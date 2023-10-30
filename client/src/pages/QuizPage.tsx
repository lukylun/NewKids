import React, { useEffect, useState } from 'react';
import PageLayout from 'layouts/common/PageLayout';
import QuizPageLayout from 'layouts/page/QuizPageLayout';
import QuizMain from 'components/organisms/quiz/QuizMain';
import QuizHowToUse from 'components/organisms/quiz/QuizHowToUse';
import QuizQuestion from 'components/organisms/quiz/QuizQuestion';
import QuizResult from 'components/organisms/quiz/QuizResult';

function QuizPage() {
	const [step, setStep] = useState(0);
	const [cnt, setCnt] = useState(0);
	const [stepView, setStepView] = useState(<div />);

	useEffect(() => {
		switch (step) {
			case 0: {
				setStepView(<QuizMain setStep={setStep} />);
				break;
			}
			case 1: {
				setStepView(<QuizHowToUse setStep={setStep} />);
				break;
			}
			case 2: {
				setStepView(<QuizQuestion setStep={setStep} setCnt={setCnt} />);
				break;
			}
			case 3: {
				setStepView(<QuizResult cnt={cnt} setStep={setStep} />);
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
			<QuizPageLayout StepView={stepView} />
		</PageLayout>
	);
}

export default QuizPage;
