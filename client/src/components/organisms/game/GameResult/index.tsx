import React, { Dispatch, SetStateAction, useEffect, useState } from 'react';
import GameCompleteLottie from 'components/atoms/lottie/GameCompleteLottie';
import Title from 'components/atoms/quiz/Title';
import Button from 'components/atoms/common/Button';
import useMovePage from 'hooks/useMovePage';
import ScrollToTop from 'components/atoms/common/ScrollToTop';
import { resultQuizApi } from 'utils/apis/quiz';
import { GameResultWrapper } from './style';

interface IGameResultProps {
	setStep: Dispatch<SetStateAction<number>>;
	cnt: number;
}

function GameResult(props: IGameResultProps) {
	const { setStep, cnt } = props;
	const [isDone, setIsDone] = useState(false);
	const [num, setNum] = useState(0);
	const [movePage] = useMovePage();
	const [score, setScore] = useState(0);

	const getResult = async () => {
		try {
			const memberkey = localStorage.getItem('memberkey');
			if (memberkey) {
				const response = await resultQuizApi(memberkey);
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
			setIsDone(true);
		}
	};

	useEffect(() => {
		if (num === 0) {
			getResult();
			setIsDone(false);
		}
	}, [num]);

	return (
		<GameResultWrapper>
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
				<Button size="s" radius="m" color="Primary" text="확인" handleClick={handleClick} />
			</div>
		</GameResultWrapper>
	);
}

export default GameResult;
