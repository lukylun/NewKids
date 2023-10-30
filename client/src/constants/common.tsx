import React from 'react';
import { ReactComponent as Home } from 'assets/icons/home.svg';
import { ReactComponent as Article } from 'assets/icons/article.svg';
import { ReactComponent as Vocabulary } from 'assets/icons/vocabulary.svg';
import { ReactComponent as Game } from 'assets/icons/game.svg';
import { ReactComponent as Quiz } from 'assets/icons/quiz.svg';

export const MENUS = [
	{ key: 0, name: '홈', path: '/', icon: <Home /> },
	{ key: 1, name: '기사찾기', path: '/article', icon: <Article /> },
	{ key: 2, name: '나만의 단어장', path: '/vocabulary', icon: <Vocabulary /> },
	{ key: 3, name: '단어 게임', path: '/game', icon: <Game /> },
	{ key: 4, name: '주간 단어 퀴즈', path: '/quiz', icon: <Quiz /> },
];

export const PERIOD_FILTER_LIST = [
	{
		key: 0,
		name: '전체',
	},
	{
		key: 1,
		name: '1일',
	},
	{
		key: 2,
		name: '1주',
	},
	{
		key: 3,
		name: '1개월',
	},
	{
		key: 4,
		name: '3개월',
	},
	{
		key: 5,
		name: '1년',
	},
];
