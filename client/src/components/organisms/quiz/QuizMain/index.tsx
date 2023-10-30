import React, { Dispatch, SetStateAction, useEffect, useState } from 'react';
import Title from 'components/atoms/quiz/Title';
import KeywordLottie from 'components/atoms/lottie/KeywordLottie';
import Explain from 'components/atoms/quiz/Explain';
import Button from 'components/atoms/common/Button';
import { QuizMainContainer } from './style';

interface IQuizMainProps {
	setStep: Dispatch<SetStateAction<number>>;
}

function QuizMain(props: IQuizMainProps) {
	const { setStep } = props;
	const [isDone, setIsDone] = useState(false);
	const [num, setNum] = useState(0);

	const handleClick = () => {
		if (!isDone) {
			setStep(1);
			setNum(1);
		}
	};

	useEffect(() => {
		if (num === 0) {
			setIsDone(false);
		}
	}, [num]);

	return (
		<QuizMainContainer>
			<Title text="" effectText="주간 단어 퀴즈" />
			<KeywordLottie />
			<Explain />
			<div className="quiz-button">
				<Button size="l" radius="m" color="SubFirst" text="시작하러 가기!" handleClick={handleClick} />
			</div>
		</QuizMainContainer>
	);
}

export default QuizMain;
