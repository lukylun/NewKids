import React, { Dispatch, SetStateAction, useEffect, useState } from 'react';
import Title from 'components/atoms/quiz/Title';
import Button from 'components/atoms/common/Button';
import GameCompleteLottie from 'components/atoms/lottie/GameCompleteLottie';
import { resultWeeklyQuizApi } from 'utils/apis/quiz';
import ScrollToTop from 'components/atoms/common/ScrollToTop';
import useMovePage from 'hooks/useMovePage';
import { QuizResultWrapper } from './style';

interface IQuizResultProps {
	setStep: Dispatch<SetStateAction<number>>;
	cnt: number;
}

function QuizResult(props: IQuizResultProps) {
	const { setStep, cnt } = props;
	const [isDone, setIsDone] = useState(false);
	const [num, setNum] = useState(0);
	const [movePage] = useMovePage();
	const [score, setScore] = useState(0);

	const getResult = async () => {
		try {
			const memberkey = localStorage.getItem('memberkey');
			if (memberkey) {
				const response = await resultWeeklyQuizApi(memberkey);
				const result = response.data.data;
				setScore(result.totalScore);
			}
		} catch (e) {
			console.log(e);
		}
	};

	const handleClick = () => {
		if (!isDone) {
			setStep(3);
			movePage('/');
			setNum(1);
		}
	};

	useEffect(() => {
		if (num === 0) {
			getResult();
			setIsDone(false);
		}
	}, [num]);

	return (
		<QuizResultWrapper>
			<ScrollToTop />
			<GameCompleteLottie />
			<div className="score-wrapper">
				<Title text="최종 점수" effectText="" />
				<h1 className="score">{score} 점</h1>
			</div>
			<div className="score-wrapper">
				<Title text="총 맞춘 문제" effectText="" />
				<h1 className="answer">{cnt} 문제</h1>
			</div>
			<div className="quiz-button">
				<Button size="s" radius="m" color="SubFirst" text="확인" handleClick={handleClick} />
			</div>
		</QuizResultWrapper>
	);
}

export default QuizResult;
