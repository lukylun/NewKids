import React from 'react';
import { IArticle } from 'types/article';
import { useNavigate } from 'react-router-dom';
import { dateToString } from 'utils/common/dateToString';
import { RectangleArticleListItemContainer } from './style';

interface IRectangleArticleListItemProps {
	article: IArticle;
}

function RectangleArticleListItem(props: IRectangleArticleListItemProps) {
	const navigate = useNavigate();
	const { article } = props;

	return (
		<RectangleArticleListItemContainer onClick={() => navigate(`/article/${article.articleId}`)}>
			<img src={article.thumbnailImg.length ? article.thumbnailImg : '/assets/imgs/default.png'} alt="" />
			<div className="content">
				<h3>{article.title}</h3>
				<div className="article-info">
					<h4>{article.writer}</h4>
					<h4>{dateToString(new Date(article.publishedDate))}</h4>
				</div>
			</div>
		</RectangleArticleListItemContainer>
	);
}

export default RectangleArticleListItem;
