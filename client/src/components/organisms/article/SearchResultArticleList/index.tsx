import React from 'react';
import { IArticle } from 'types/article';
import SearchResultArticleListItem from 'components/atoms/article/SearchResultArticleListItem';
import { useSearchParams } from 'react-router-dom';
import { SearchResultArticleListContainer } from './style';

interface ISearchResultArticleListProps {
	articles: IArticle[];
	totalElements: number;
}

function SearchResultArticleList(props: ISearchResultArticleListProps) {
	const { articles, totalElements } = props;
	const [searchParams] = useSearchParams();

	return (
		<SearchResultArticleListContainer>
			<h3>{searchParams.size ? <>{searchParams.get('search')}(으)로 검색한 결과입니다.</> : <div />}</h3>
			{totalElements ? <h3>총 {totalElements}건의 기사 검색결과가 있습니다.</h3> : <div>검색된 기사가 없습니다.</div>}
			{articles.length ? (
				articles.map((el) => <SearchResultArticleListItem key={el.articleId} article={el} />)
			) : (
				<div />
			)}
		</SearchResultArticleListContainer>
	);
}

export default SearchResultArticleList;
