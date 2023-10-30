import React, { ReactNode } from 'react';
import { NarrowContentLayout } from 'layouts/common/ContentLayout';
import { LoginPageLayoutContainer } from './style';

interface ILoginPageLayoutProps {
	Title: ReactNode;
	LoginForm: ReactNode;
	Footer: ReactNode;
}
function LoginPageLayout({ Title, LoginForm, Footer }: ILoginPageLayoutProps) {
	return (
		<LoginPageLayoutContainer>
			<div className="title">
				<NarrowContentLayout>{Title}</NarrowContentLayout>
			</div>
			<div className="login-form">
				<NarrowContentLayout>{LoginForm}</NarrowContentLayout>
			</div>
			<div className="footer">
				<NarrowContentLayout>{Footer}</NarrowContentLayout>
			</div>
		</LoginPageLayoutContainer>
	);
}

export default LoginPageLayout;
