import {
	QuizAnswerCheckApiBody,
	QuizQuestionRequestApiBody,
	WeeklyQuizAnswerCheckApiBody,
	WeeklyQuizQuestionRequestApiBody,
} from 'types/api';

export const DUMMY_QUIZS: QuizQuestionRequestApiBody[] = [
	{ no: 1, word: '업적', description: '어떤 사업이나 연구 따위에서 세운 공적을 뜻한다.' },
	{ no: 2, word: '정원', description: '집 안에 있는 뜰이나 꽃밭, 일정한 규정에 의하여 정한 인원' },
	{ no: 3, word: '금일', description: '지금 지나가고 있는 바로 이날, 이제까지의 매우 짧은 동안' },
	{
		no: 4,
		word: '양성',
		description: '가르쳐서 유능한 사람을 길러낸다. 병을 진단하기 위하여 검사를 한 결과 특정한 반응이 나타나는 일',
	},
	{ no: 5, word: '심심', description: '여래의 본원을 깊이 믿는 마음. 온갖 선행을 닦고자 하는 굳은 마음' },
	{ no: 6, word: '참여', description: '어떤 일에 끼어들어 관계함' },
	{
		no: 7,
		word: '기각',
		description: '소송을 수리한 법원이 형식적인 요건을 갖추었으나 그 내용이 이유가 없다고 취소하는 일',
	},
	{ no: 8, word: '대치', description: '다른 것으로 바꾸어 놓음, 서로 맞서서 버팀' },
	{ no: 9, word: '실기', description: '실제의 기능이나 기술' },
	{ no: 10, word: '지양', description: '더 높은 단계로 오르기 위하여 어떠한 것을 하지 아니함' },
];

export const DUMMY_ANSWERS_1: QuizAnswerCheckApiBody[] = [{ answer: '업적' }];

export const DUMMY_WEEKLY_QUIZS: WeeklyQuizQuestionRequestApiBody[] = [
	{
		no: 1,
		answerWord: '미스티',
		description: '',
		contents: [],
	},
	{
		no: 2,
		answerWord: '파이낸셜타임스',
		description: '',
		contents: [],
	},
	{
		no: 3,
		answerWord: '청구권',
		description: '',
		contents: [],
	},
	{
		no: 4,
		answerWord: '제우스',
		description: '',
		contents: [],
	},
	{
		no: 5,
		answerWord: '국제선',
		description: '',
		contents: [],
	},
];

export const DUMMY_WEEKLY_ANSWERS_1: WeeklyQuizAnswerCheckApiBody[] = [{ answer: '아스날' }];
