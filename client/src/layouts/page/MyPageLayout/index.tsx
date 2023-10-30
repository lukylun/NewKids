import React, { ReactNode } from 'react';
import { ContentLayout } from 'layouts/common/ContentLayout';
import { MyPageLayoutContainer } from './style';

interface IMyPageLayoutProps {
	MyPageMenu: ReactNode;
	UserDetail: ReactNode;
	UserFigures: ReactNode;
}

function MyPageLayout({ MyPageMenu, UserDetail, UserFigures }: IMyPageLayoutProps) {
	return (
		<MyPageLayoutContainer>
			<div className="my-page-menu">
				<ContentLayout>{MyPageMenu}</ContentLayout>
			</div>
			<ContentLayout>
				<div className="user-info">
					{UserFigures}
					{UserDetail}
				</div>
			</ContentLayout>
		</MyPageLayoutContainer>
	);
}

export default MyPageLayout;
