import React, { useState, useEffect } from 'react';
import PageLayout from 'layouts/common/PageLayout';
import JoinPageLayout from 'layouts/page/JoinPageLayout';
import TermsOfService from 'components/organisms/auth/TermsOfService';
import JoinForm from 'components/organisms/auth/JoinForm';

function JoinPage() {
	const [step, setStep] = useState(0);
	const [stepView, setStepView] = useState(<div />);

	useEffect(() => {
		switch (step) {
			case 0:
				setStepView(<TermsOfService onNext={() => setStep(step + 1)} />);
				break;
			case 1:
				setStepView(<JoinForm />);
				break;
			default:
				break;
		}
	}, [step]);

	return (
		<PageLayout reducePadding>
			<JoinPageLayout StepView={stepView} />
		</PageLayout>
	);
}

export default JoinPage;
