import React, { Dispatch, SetStateAction, useEffect, useState } from 'react';
import Swal from 'sweetalert2';
import Title from 'components/atoms/game/Title';
import Question from 'components/atoms/quiz/Question';
import Input from 'components/atoms/common/Input';
import { DUMMY_QUIZS } from 'constants/dummyquiz';
import { QuizQuestionRequestApiBody } from 'types/api';
import SoundBarLottie from 'components/atoms/lottie/SoundBarLottie';
import { getSpeech } from 'utils/common/tts';
import Button from 'components/atoms/common/Button';
import { checkAnswerApi, getQuizQuestionApi } from 'utils/apis/quiz';
import ScrollToTop from 'components/atoms/common/ScrollToTop';
import { GameQuestionContainer } from './style';

interface IGameQuestionProps {
	setStep: Dispatch<SetStateAction<number>>;
	setCnt: Dispatch<SetStateAction<number>>;
}

function GameQuestion(props: IGameQuestionProps) {
	const { setStep, setCnt } = props;
	const [isDone, setIsDone] = useState(false);
	const [question, setQuestion] = useState<QuizQuestionRequestApiBody[]>(DUMMY_QUIZS);
	const [currentIndex, setCurrentIndex] = useState(0);
	const [num, setNum] = useState(0);
	const [answer, setAnswer] = useState('');

	const getQuizQuestions = async () => {
		try {
			const memberkey = localStorage.getItem('memberkey');
			if (memberkey) {
				const response = await getQuizQuestionApi(memberkey);
				const quiz = response.data.data;
				setQuestion([quiz]);
			}
		} catch (e) {
			console.log(e);
		}
	};

	const handleSpeechButton = () => {
		const voiceValue = question[0].word;
		getSpeech(voiceValue);
	};

	const handleClick = async (selectedAnswer: string) => {
		const correctAnswer = question[0].word;
		try {
			const memberkey = localStorage.getItem('memberkey');

			const body = {
				answer: selectedAnswer,
			};

			if (memberkey) {
				await checkAnswerApi(memberkey, body);
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
						setAnswer('');
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
						setAnswer('');
						setNum(0);
					});
				}
			}
		} catch (e) {
			console.log(e);
		}
	};

	const nextLevelClick = async (selectedAnswer: string) => {
		const correctAnswer = question[0].word;

		try {
			const memberkey = localStorage.getItem('memberkey');

			const body = {
				answer: selectedAnswer,
			};

			if (memberkey) {
				await checkAnswerApi(memberkey, body);
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
		<GameQuestionContainer>
			<ScrollToTop />
			<div className="question-box">
				<Title effectText={question[0].no} text="번 문제" />
				<div className="question-wrapper">
					<div className="question">
						<Question text={question[0].description} />
					</div>
					<button type="button" onClick={handleSpeechButton}>
						<SoundBarLottie />
					</button>
				</div>
				<hr className="hr" />
				<div className="input-wrapper">
					<Input type="text" value={answer} setValue={setAnswer} placeholder="정답을 입력해주세요." />
					<div className="button-wrapper">
						{currentIndex === 9 ? (
							<Button size="s" radius="m" color="Primary" text="확인" handleClick={() => nextLevelClick(answer)} />
						) : (
							<Button size="s" radius="m" color="Primary" text="확인" handleClick={() => handleClick(answer)} />
						)}
					</div>
				</div>
			</div>
		</GameQuestionContainer>
	);
}

export default GameQuestion;
