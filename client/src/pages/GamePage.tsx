import React, { useEffect, useState } from 'react';
import PageLayout from 'layouts/common/PageLayout';
import GamePageLayout from 'layouts/page/GamePageLayout';
import GameMain from 'components/organisms/game/GameMain';
import GameHowToUse from 'components/organisms/game/GameHowToUse';
import GameResult from 'components/organisms/game/GameResult';
import GameQuestion from 'components/organisms/game/GameQuestion';

function GamePage() {
	const [step, setStep] = useState(0);
	const [cnt, setCnt] = useState(0);
	const [stepView, setStepView] = useState(<div />);

	useEffect(() => {
		switch (step) {
			case 0: {
				setStepView(<GameMain setStep={setStep} />);
				break;
			}
			case 1: {
				setStepView(<GameHowToUse setStep={setStep} />);
				break;
			}
			case 2: {
				setStepView(<GameQuestion setStep={setStep} setCnt={setCnt} />);
				break;
			}
			case 3: {
				setStepView(<GameResult cnt={cnt} setStep={setStep} />);
				break;
			}
			default: {
				setStepView(<div>에러페이지입니다.</div>);
				break;
			}
		}
	}, [step]);

	return (
		<PageLayout>
			<GamePageLayout StepView={stepView} />
		</PageLayout>
	);
}

export default GamePage;
