package com.pablojuice.framework.api;

import org.testng.annotations.Test;

public class TrelloApiTest {
	private static final String KEY = "f037495712ba064a957e97dad510b26b";
	private static final String TOKEN = "f02f1be70c984f4535604d200aa9d074e0fa2eb12f1f937a9ed8076d6fd31816";

	private final TrelloApiBiz trelloApiBiz = new TrelloApiBiz(KEY, TOKEN);

	@Test
	public void testTrelloBoardApi() {
		trelloApiBiz
				.createBoard()
				.checkIfBoardExists()
				.renameBoard()
				.deleteBoard();
	}

	@Test
	public void testTrelloCardListApi() {
		trelloApiBiz
				.createBoard()
				.checkIfBoardExists()
				.createCardList()
				.createCard()
				.createCard()
				.createCard()
				.deleteBoard();
	}

	@Test
	public void testTrelloCardApi() {
		trelloApiBiz
				.createBoard()
				.checkIfBoardExists()
				.createCardList()
				.createCard().editCard()
				.createCard().commentCard()
				.createCard().deleteCard()
				.deleteBoard();
	}

	@Test
	public void testTrelloMoveApi() {
		trelloApiBiz
				.createBoard()
				.checkIfBoardExists()
				.createCardList()
				.createCard()
				.createCard()
				.createCard()
				.createCardList()
				.moveCardsToAnotherList()
				.deleteBoard();
	}

	@Test
	public void testTrelloOrgApi() {
		trelloApiBiz
				.createOrganisation()
				.createBoard()
				.checkIfBoardExists()
				.renameBoard()
				.deleteBoard()
				.deleteOrganisation();
	}
}
