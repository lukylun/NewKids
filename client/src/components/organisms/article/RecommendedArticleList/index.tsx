import React, { useState, useEffect } from 'react';
import { IArticle } from 'types/article';
import RectangleArticleListItem from 'components/atoms/article/RectangleArticleListItem';
import { getAllRecommededPeerArticleApi } from 'utils/apis/article';
import { RecommendedArticleListContainer } from './style';

function RecommendedArticleList() {
	const [articles, setArticles] = useState<IArticle[]>([]);

	// TODO : 추천 API 나오면 교체
	const fetchData = async () => {
		try {
			const response = await getAllRecommededPeerArticleApi();
			setArticles(response.data.data);
		} catch (error) {
			console.error(error);
		}
	};

	useEffect(() => {
		fetchData();
	}, []);

	return (
		<RecommendedArticleListContainer>
			{articles.length ? articles.map((el) => <RectangleArticleListItem article={el} key={el.articleId} />) : <div />}
		</RecommendedArticleListContainer>
	);
}

export default RecommendedArticleList;
