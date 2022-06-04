package com.pablojuice.framework.api;

import com.pablojuice.framework.base.BaseBiz;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TrelloApiBiz extends BaseBiz {

	private static final String BASE_URI = "https://api.trello.com";
	private static final String BASE_PATH = "/1";

	private final String apiKey;
	private final String apiToken;

	private final List<String> boardIDs = new ArrayList<>();
	private final List<String> listIDs = new ArrayList<>();
	private final List<String> cardIDs = new ArrayList<>();
	private final List<String> orgIDs = new ArrayList<>();

	public TrelloApiBiz(String apiKey, String apiToken) {
		this.apiKey = apiKey;
		this.apiToken = apiToken;
		RestAssured.baseURI = BASE_URI;
		RestAssured.basePath = BASE_PATH;
	}

	public TrelloApiBiz createBoard() {
		String randomBoardName = "newBoard" + getRandomString();
		String boardID = givenTrelloRequest()
				.queryParam("name", randomBoardName)
				.post("/boards")
				.then()
				.statusCode(StatusCode.OK.value)
				.contentType(ContentType.JSON)
				.assertThat()
				.body("name", equalTo(randomBoardName))
				.extract().path("id");
		Assert.assertNotNull(boardID);
		boardIDs.add(boardID);
		return this;
	}

	public TrelloApiBiz checkIfBoardExists() {
		Assert.assertNotNull(getLatestBoardID(), "Board id is null");
		givenTrelloRequest()
				.get("/boards/" + getLatestBoardID())
				.then()
				.assertThat()
				.statusCode(StatusCode.OK.value);
		return this;
	}

	public TrelloApiBiz renameBoard() {
		Assert.assertNotNull(getLatestBoardID(), "Board id is null");
		String newBoardName = "newBoard" + getRandomString();
		givenTrelloRequest()
				.queryParam("name", newBoardName)
				.put("/boards/" + getLatestBoardID())
				.then()
				.statusCode(StatusCode.OK.value)
				.contentType(ContentType.JSON)
				.assertThat()
				.body("name", equalTo(newBoardName));
		return this;
	}

	public TrelloApiBiz deleteBoard() {
		Assert.assertNotNull(getLatestBoardID(), "Board id is null");
		givenTrelloRequest()
				.delete("/boards/" + getLatestBoardID())
				.then()
				.statusCode(StatusCode.OK.value);
		return this;
	}

	public TrelloApiBiz createCardList() {
		Assert.assertNotNull(getLatestBoardID(), "Board id is null");
		String randomListName = "newList" + getRandomString();
		listIDs.add(
				givenTrelloRequest()
						.queryParam("name", randomListName)
						.post("/boards/" + getLatestBoardID() + "/lists")
						.then()
						.statusCode(StatusCode.OK.value)
						.contentType(ContentType.JSON)
						.assertThat()
						.body("name", equalTo(randomListName))
						.extract().path("id")
		);
		return this;
	}

	public TrelloApiBiz moveCardsToAnotherList() {
		Assert.assertNotNull(getLatestListID(), "List id is null");
		Assert.assertTrue(listIDs.size() > 1);
		givenTrelloRequest()
				.queryParam("idList", getLatestListID())
				.queryParam("idBoard", getLatestBoardID())
				.post("/lists/" + listIDs.get(listIDs.size() - 2) + "/moveAllCards")
				.then()
				.statusCode(StatusCode.OK.value)
				.contentType(ContentType.JSON);
		return this;
	}

	public TrelloApiBiz createCard() {
		Assert.assertNotNull(getLatestListID(), "List id is null");
		String randomCardName = "newCard" + getRandomString();
		cardIDs.add(
				givenTrelloRequest()
						.queryParam("name", randomCardName)
						.queryParam("idList", getLatestListID())
						.queryParam("desc", "initial Test Description")
						.post("/cards")
						.then()
						.statusCode(StatusCode.OK.value)
						.contentType(ContentType.JSON)
						.assertThat()
						.body("name", equalTo(randomCardName))
						.extract().path("id")
		);
		return this;
	}

	public TrelloApiBiz editCard() {
		Assert.assertNotNull(getLatestCardID(), "Card id is null");
		givenTrelloRequest()
				.queryParam("name", "newCard0")
				.queryParam("desc", "edited Test Description")
				.put("/cards/" + getLatestCardID())
				.then()
				.statusCode(StatusCode.OK.value)
				.contentType(ContentType.JSON)
				.assertThat()
				.body("desc", equalTo("edited Test Description"))
				.extract().path("id");
		return this;
	}

	public TrelloApiBiz commentCard() {
		Assert.assertNotNull(getLatestCardID(), "Card id is null");
		givenTrelloRequest()
				.queryParam("text", "testComment")
				.post("/cards/" + getLatestCardID() + "/actions/comments")
				.then()
				.statusCode(StatusCode.OK.value);
		return this;
	}

	public TrelloApiBiz deleteCard() {
		Assert.assertNotNull(getLatestCardID(), "Card id is null");
		givenTrelloRequest()
				.delete("/cards/" + getLatestCardID())
				.then()
				.statusCode(StatusCode.OK.value);
		return this;
	}

	public TrelloApiBiz createOrganisation() {
		orgIDs.add(
				givenTrelloRequest()
						.queryParam("displayName", "newOrg" + getRandomString())
						.when()
						.post("/organizations")
						.then()
						.statusCode(StatusCode.OK.value)
						.extract()
						.path("id")
		);
		Assert.assertNotNull(getLatestOrgID(), "Org id is null");
		return this;
	}

	public TrelloApiBiz deleteOrganisation() {
		Assert.assertNotNull(getLatestOrgID(), "Org id is null");
		givenTrelloRequest()
				.when()
				.delete("/organizations/" + getLatestOrgID())
				.then()
				.statusCode(StatusCode.OK.value);
		return this;
	}

	private String getLatestBoardID() {
		if (boardIDs.size() > 0) {
			return boardIDs.get(boardIDs.size() - 1);
		}
		return null;
	}

	private String getLatestListID() {
		if (listIDs.size() > 0) {
			return listIDs.get(listIDs.size() - 1);
		}
		return null;
	}

	private String getLatestCardID() {
		if (cardIDs.size() > 0) {
			return cardIDs.get(cardIDs.size() - 1);
		}
		return null;
	}

	private String getLatestOrgID() {
		if (orgIDs.size() > 0) {
			return orgIDs.get(orgIDs.size() - 1);
		}
		return null;
	}

	private RequestSpecification givenTrelloRequest() {
		return given()
				.contentType(ContentType.JSON)
				.log().all()
				.when()
				.queryParam("key", apiKey)
				.queryParam("token", apiToken);
	}

	private static String getRandomString() {
		return UUID.randomUUID().toString();
	}
}
