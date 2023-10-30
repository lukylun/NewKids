import React, { ReactNode } from 'react';
import { ContentLayout } from 'layouts/common/ContentLayout';
import { GamePageLayoutWrapper } from './style';

interface IGamePageLayoutProps {
	StepView: ReactNode;
}

function GamePageLayout(props: IGamePageLayoutProps) {
	const { StepView } = props;
	return (
		<GamePageLayoutWrapper>
			<ContentLayout>
				<div className="step-view">{StepView}</div>
			</ContentLayout>
		</GamePageLayoutWrapper>
	);
}

export default GamePageLayout;
