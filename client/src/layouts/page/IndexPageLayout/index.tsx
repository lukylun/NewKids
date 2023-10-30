import React, { ReactNode } from 'react';
import { ContentLayout, FullContentLayout } from 'layouts/common/ContentLayout';
import { IndexPageLayoutContainer } from './style';

interface IIndexPageLayoutProps {
	PopularArticleList: ReactNode;
	TrendingKeywordList: ReactNode;
	RecommendedArticleList: ReactNode;
	WordCloud: ReactNode;
	Footer: ReactNode;
}

function IndexPageLayout(props: IIndexPageLayoutProps) {
	const { PopularArticleList, TrendingKeywordList, RecommendedArticleList, WordCloud, Footer } = props;
	return (
		<IndexPageLayoutContainer>
			<ContentLayout>
				<div className="popular-article-list">{PopularArticleList}</div>
				<div className="trending-keyword-list">{TrendingKeywordList}</div>
				<div className="recommended-article-list">{RecommendedArticleList}</div>
				<div className="word-cloud">{WordCloud}</div>
			</ContentLayout>
			<FullContentLayout>
				<div className="footer">{Footer}</div>
			</FullContentLayout>
		</IndexPageLayoutContainer>
	);
}

export default IndexPageLayout;
