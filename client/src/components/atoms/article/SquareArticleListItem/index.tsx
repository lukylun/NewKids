import React from 'react';
import { IArticle } from 'types/article';
import { SquareArticleListItemContainer } from './style';

interface ISquareArticleListItemProps {
	article: IArticle;
	handleClick: () => void;
}
function SquareArticleListItem(props: ISquareArticleListItemProps) {
	const { article, handleClick } = props;

	return (
		<SquareArticleListItemContainer onClick={handleClick}>
			<img src={article.thumbnailImg.length ? article.thumbnailImg : '/assets/imgs/default.png'} alt="" />
			<h2>{article.title}</h2>
			<div className="overlay" />
		</SquareArticleListItemContainer>
	);
}

export default SquareArticleListItem;
