import React, { useState, useEffect } from 'react';
import SquareArticleListItem from 'components/atoms/article/SquareArticleListItem';
import { IArticle } from 'types/article';
import { getAllRecommendedArticleApi } from 'utils/apis/article';
import useMovePage from 'hooks/useMovePage';
import { PopularArticleListContainer } from './style';

function PopularArticleList() {
	const [articles, setArticles] = useState<IArticle[]>([]);
	const [movePage] = useMovePage();

	// TODO : API 데이터로 set하기
	const fetchData = async () => {
		try {
			const response = await getAllRecommendedArticleApi();
			if (response.status === 200) {
				setArticles(response.data.data);
			}
		} catch (error) {
			console.error(error);
		}
	};

	useEffect(() => {
		fetchData();
	}, []);

	return (
		<PopularArticleListContainer>
			{articles.length ? (
				articles.map((ar) => (
					<SquareArticleListItem
						key={ar.articleId}
						article={ar}
						handleClick={() => movePage(`/article/${ar.articleId}`)}
					/>
				))
			) : (
				<div />
			)}{' '}
		</PopularArticleListContainer>
	);
}

export default PopularArticleList;
