import React from 'react';
import PageLayout from 'layouts/common/PageLayout';
import MyPageLayout from 'layouts/page/MyPageLayout';
import UserDetail from 'components/organisms/mypage/UserDetail';
import MyPageMenu from 'components/organisms/common/MyPageMenu';
import UserFigures from 'components/organisms/mypage/UserFigures';

function MyPage() {
	return (
		<PageLayout>
			<MyPageLayout MyPageMenu={<MyPageMenu />} UserFigures={<UserFigures />} UserDetail={<UserDetail />} />
		</PageLayout>
	);
}

export default MyPage;
