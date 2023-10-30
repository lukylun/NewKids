import React from 'react';
import PageLayout from 'layouts/common/PageLayout';
import LoginPageLayout from 'layouts/page/LoginPageLayout';
import Title from 'components/atoms/auth/Title';
import LoginForm from 'components/organisms/auth/LoginForm';
import LoginFooter from 'components/organisms/auth/LoginFooter';

function LoginPage() {
	return (
		<PageLayout reducePadding>
			<LoginPageLayout Title={<Title />} LoginForm={<LoginForm />} Footer={<LoginFooter />} />
		</PageLayout>
	);
}

export default LoginPage;
