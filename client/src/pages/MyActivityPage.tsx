import React, { useEffect, useState } from 'react';
import PageLayout from 'layouts/common/PageLayout';
import MyActivityLayout from 'layouts/page/MyActivityLayout';
import MyActivityChart from 'components/organisms/myactivity/MyActivityChart';
import MyActivityArticle from 'components/organisms/myactivity/MyActivityArticle';
import MyPageMenu from 'components/organisms/common/MyPageMenu';
import { findAllReadArticleApi } from 'utils/apis/article';
import Pagination from 'components/organisms/common/Pagination';

function MyActivityPage() {
	const [currentPage, setCurrentPage] = useState(1);
	const [totalPages, setTotalPages] = useState(3);
	const [size, setSize] = useState(0);
	const [currentGroup, setCurrentGroup] = useState(1);
	const [articles, setArticles] = useState([]);

	const searchPage = async () => {
		try {
			const memberkey = localStorage.getItem('memberkey');
			if (memberkey) {
				const response = await findAllReadArticleApi(memberkey, currentPage);
				if (response.status === 200) {
					setArticles(response.data.data.content);
					setTotalPages(response.data.data.totalPages);
					setSize(response.data.data.size);
				}
			}
		} catch (error) {
			console.log(error);
		}
	};

	useEffect(() => {
		searchPage();
	}, [currentPage]);

	return (
		<PageLayout>
			<MyActivityLayout
				MyPageMenu={<MyPageMenu />}
				MyActivityChart={<MyActivityChart />}
				MyActivityArticle={<MyActivityArticle articles={articles} />}
				Pagination={
					<Pagination
						currentPage={currentPage}
						totalPages={totalPages}
						setCurrentPage={setCurrentPage}
						size={size}
						currentGroup={currentGroup}
						setCurrentGroup={setCurrentGroup}
					/>
				}
			/>
		</PageLayout>
	);
}

export default MyActivityPage;
