import React, { ReactNode } from 'react';
import { ContentLayout } from 'layouts/common/ContentLayout';
import { QuizPageLayoutContainer } from './style';

interface IQuizPageLayoutProps {
	StepView: ReactNode;
}

function QuizPageLayout(props: IQuizPageLayoutProps) {
	const { StepView } = props;
	return (
		<QuizPageLayoutContainer>
			<ContentLayout>
				<div className="step-view">{StepView}</div>
			</ContentLayout>
		</QuizPageLayoutContainer>
	);
}

export default QuizPageLayout;
