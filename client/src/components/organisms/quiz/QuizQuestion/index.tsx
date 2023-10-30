import React, { Dispatch, SetStateAction, useEffect, useState } from 'react';
import Title from 'components/atoms/quiz/Title';
import Question from 'components/atoms/quiz/Question';
import { WeeklyQuizQuestionRequestApiBody } from 'types/api';
import { DUMMY_WEEKLY_QUIZS } from 'constants/dummyquiz';
import QuizButton from 'components/atoms/common/QuizButton';
import Swal from 'sweetalert2';
import { checkWeeklyAnswerApi, getWeeklyQuizQuestionApi } from 'utils/apis/quiz';
import ScrollToTop from 'components/atoms/common/ScrollToTop';
import { QuizQuestionContainer } from './style';

interface IQuizQuestionProps {
	setStep: Dispatch<SetStateAction<number>>;
	setCnt: Dispatch<SetStateAction<number>>;
}

function QuizQuestion(props: IQuizQuestionProps) {
	const { setStep, setCnt } = props;
	const [isDone, setIsDone] = useState(false);
	const [question, setQuestion] = useState<WeeklyQuizQuestionRequestApiBody[]>(DUMMY_WEEKLY_QUIZS);
	const [num, setNum] = useState(0);
	const [currentIndex, setCurrentIndex] = useState(0);

	const getQuizQuestions = async () => {
		try {
			const memberkey = localStorage.getItem('memberkey');

			if (memberkey) {
				const response = await getWeeklyQuizQuestionApi(memberkey);
				const weeklyQuiz = response.data.data;
				setQuestion([weeklyQuiz]);
			}
		} catch (e) {
			console.log(e);
		}
	};

	const handleClick = async (selectedAnswer: string) => {
		const correctAnswer = question[0].answerWord;
		try {
			const memberkey = localStorage.getItem('memberkey');

			const body = {
				answer: selectedAnswer,
			};

			if (memberkey) {
				await checkWeeklyAnswerApi(memberkey, body);
			}

			if (!isDone) {
				if (selectedAnswer === correctAnswer) {
					Swal.fire({
						imageUrl: 'https://ifh.cc/g/Y4p2ln.gif',
						imageHeight: 200,
						title: '축하해요! 정답입니다.',
						confirmButtonText: '확인',
					}).then(() => {
						setCurrentIndex((prevIndex) => prevIndex + 1);
						setIsDone(true);
						setCnt((prevIndex) => prevIndex + 1);
						setNum(0);
					});
				} else {
					Swal.fire({
						imageUrl: 'https://ifh.cc/g/4yzNys.gif',
						imageHeight: 200,
						title: '아쉬워요... 오답입니다.',
						confirmButtonText: '확인',
					}).then(() => {
						setCurrentIndex((prevIndex) => prevIndex + 1);
						setIsDone(true);
						setNum(0);
					});
				}
			}
		} catch (e) {
			console.log(e);
		}
	};

	const nextLevelClick = async (selectedAnswer: string) => {
		const correctAnswer = question[0].answerWord;
		try {
			const memberkey = localStorage.getItem('memberkey');

			const body = {
				answer: selectedAnswer,
			};

			if (memberkey) {
				await checkWeeklyAnswerApi(memberkey, body);
			}

			if (!isDone) {
				if (selectedAnswer === correctAnswer) {
					Swal.fire({
						imageUrl: 'https://ifh.cc/g/Y4p2ln.gif',
						imageHeight: 200,
						title: '축하해요! 정답입니다.',
						confirmButtonText: '확인',
					}).then(() => {
						setStep(3);
						setCnt((prevIndex) => prevIndex + 1);
						setNum(0);
					});
				} else {
					Swal.fire({
						imageUrl: 'https://ifh.cc/g/4yzNys.gif',
						imageHeight: 200,
						title: '아쉬워요... 오답입니다.',
						confirmButtonText: '확인',
					}).then(() => {
						setStep(3);
						setCnt((prevIndex) => prevIndex);
						setNum(0);
					});
				}
			}
		} catch (e) {
			console.log(e);
		}
	};

	useEffect(() => {
		if (num === 0) {
			getQuizQuestions();
			setNum(1);
		}
	}, [num]);

	useEffect(() => {
		setIsDone(false);
	});

	return (
		<QuizQuestionContainer>
			<ScrollToTop />
			<div className="question-box">
				<Title effectText={question[0].no} text="번 문제" />
				<Question text={question[0].description.replaceAll(question[0].answerWord, 'OOO')} />
				<hr className="hr" />
				<div className="quiz-button">
					{currentIndex === 4 ? (
						<>
							<QuizButton
								color="Normal"
								text={question[0].contents[0]}
								handleClick={() => nextLevelClick(question[0].contents[0])}
							/>
							<QuizButton
								color="Normal"
								text={question[0].contents[1]}
								handleClick={() => nextLevelClick(question[0].contents[1])}
							/>
							<QuizButton
								color="Normal"
								text={question[0].contents[2]}
								handleClick={() => nextLevelClick(question[0].contents[2])}
							/>
							<QuizButton
								color="Normal"
								text={question[0].contents[3]}
								handleClick={() => nextLevelClick(question[0].contents[3])}
							/>
						</>
					) : (
						<>
							<QuizButton
								color="Normal"
								text={question[0].contents[0]}
								handleClick={() => handleClick(question[0].contents[0])}
							/>
							<QuizButton
								color="Normal"
								text={question[0].contents[1]}
								handleClick={() => handleClick(question[0].contents[1])}
							/>
							<QuizButton
								color="Normal"
								text={question[0].contents[2]}
								handleClick={() => handleClick(question[0].contents[2])}
							/>
							<QuizButton
								color="Normal"
								text={question[0].contents[3]}
								handleClick={() => handleClick(question[0].contents[3])}
							/>
						</>
					)}
				</div>
			</div>
		</QuizQuestionContainer>
	);
}

export default QuizQuestion;
