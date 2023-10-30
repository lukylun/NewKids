import React from 'react';
import { IArticle } from 'types/article';
import useMovePage from 'hooks/useMovePage';
import { dateToString } from 'utils/common/dateToString';
import { SearchResultArticleListItemContainer } from './style';

interface ISearchResultArticleListItemProps {
	article: IArticle;
}

function SearchResultArticleListItem(props: ISearchResultArticleListItemProps) {
	const [movePage] = useMovePage();
	const { article } = props;

	return (
		<SearchResultArticleListItemContainer onClick={() => movePage(`/article/${article.articleId}`)}>
			<img src={article.thumbnailImg.length ? article.thumbnailImg : '/assets/imgs/default.png'} alt="" />
			<div className="article">
				<div className="content">
					<h3>{article.title}</h3>
					<p>{article.content}...</p>
				</div>
				<div className="article-info">
					<h4>{article.writer}</h4>
					<h4>{dateToString(new Date(article.publishedDate))}</h4>
				</div>
			</div>
		</SearchResultArticleListItemContainer>
	);
}

export default SearchResultArticleListItem;
