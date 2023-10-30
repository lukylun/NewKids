import React, { Dispatch, SetStateAction, useEffect, useState } from 'react';
import useMovePage from 'hooks/useMovePage';
import Title from 'components/atoms/game/Title';
import HowToUseExplainOne from 'components/atoms/game/HowToUseExplainOne';
import HowToUseExplainTwo from 'components/atoms/game/HowToUseExplainTwo';
import HowToUseExplainThree from 'components/atoms/game/HowToUseExplainThree';
import Button from 'components/atoms/common/Button';
import { startQuizApi } from 'utils/apis/quiz';
import HowToUseLottie from 'components/atoms/lottie/HowToUseLottie';
import ScrollToTop from 'components/atoms/common/ScrollToTop';
import { getAllVocabularyApi } from 'utils/apis/vocabulary';
import EmptyBoxLottie from 'components/atoms/lottie/EmptyBoxLottie';
import { GameHowToUseContainer } from './style';

interface IGameHowToUseProps {
	setStep: Dispatch<SetStateAction<number>>;
}

function GameHowToUse({ setStep }: IGameHowToUseProps) {
	const [movePage] = useMovePage();
	const [isDone, setIsDone] = useState(false);
	const [num, setNum] = useState(0);
	const [allVoca, setAllVoca] = useState(0);

	const articleButton = () => {
		if (!isDone) {
			movePage('/article');
		}
	};

	const getAllVocabulary = async () => {
		try {
			const memberkey = localStorage.getItem('memberkey');
			if (memberkey) {
				const response = await getAllVocabularyApi(memberkey, 1, false);
				if (response.status === 200) {
					setAllVoca(response.data.data.totalElements);
				}
			}
		} catch (e) {
			console.log(e);
		}
	};

	const startQuiz = async () => {
		try {
			const memberkey = localStorage.getItem('memberkey');
			if (memberkey) {
				await startQuizApi(memberkey);
			}
		} catch (e) {
			console.log(e);
		}
	};

	const handleClick = () => {
		if (!isDone) {
			setStep(2);
			setNum(1);
			setIsDone(true);
		}
	};

	useEffect(() => {
		if (num === 0) {
			getAllVocabulary();
			startQuiz();
			setIsDone(false);
		}
	}, [num]);

	return (
		<GameHowToUseContainer>
			<ScrollToTop />
			{allVoca < 10 ? (
				<div>
					<EmptyBoxLottie />
					<Title effectText="이런! 단어장에 단어가 부족해요!" text=" " />
					<h1 className="h1">단어장에 10개 이상의 단어가 필요해요.</h1>
					<h1 className="h1 margin-top-1">지금 기사를 읽고, 단어장에 단어를 채우러 가볼까요?</h1>
					<div className="quiz-button">
						<Button size="m" radius="m" color="Primary" text="기사 보러가기" handleClick={articleButton} />
					</div>
				</div>
			) : (
				<div>
					<HowToUseLottie />
					<Title effectText="단어 듣고 맞추기" text=" 이용방법" />
					<HowToUseExplainOne />
					<HowToUseExplainTwo />
					<HowToUseExplainThree />
					<div className="quiz-button">
						<Button size="s" radius="m" color="Primary" text="다음" handleClick={handleClick} />
					</div>
				</div>
			)}
		</GameHowToUseContainer>
	);
}

export default GameHowToUse;
