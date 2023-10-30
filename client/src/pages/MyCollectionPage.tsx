import MyPageMenu from 'components/organisms/common/MyPageMenu';
import Pagination from 'components/organisms/common/Pagination';
import MyCollectionArticle from 'components/organisms/mycollection/MyCollectionArticle';
import PageLayout from 'layouts/common/PageLayout';
import MyCollectionLayout from 'layouts/page/MyCollectionLayout';
import React, { useEffect, useState } from 'react';
import { findAllReadArticleApi } from 'utils/apis/article';

function MyCollectionPage() {
	const [currentPage, setCurrentPage] = useState(1);
	const [totalPages, setTotalPages] = useState(3);
	const [size, setSize] = useState(8);
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
			<MyCollectionLayout
				MyPageMenu={<MyPageMenu />}
				MyCollectionArticle={<MyCollectionArticle articles={articles} />}
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

export default MyCollectionPage;
