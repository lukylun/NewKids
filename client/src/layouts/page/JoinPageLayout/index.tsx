import React, { ReactNode } from 'react';
import { NarrowContentLayout } from 'layouts/common/ContentLayout';
import { JoinPageLayoutContainer } from './style';

interface IJoinPageLayoutProps {
	StepView: ReactNode;
}
function JoinPageLayout({ StepView }: IJoinPageLayoutProps) {
	return (
		<JoinPageLayoutContainer>
			<NarrowContentLayout>
				<div className="step-view">{StepView}</div>
			</NarrowContentLayout>
		</JoinPageLayoutContainer>
	);
}

export default JoinPageLayout;
