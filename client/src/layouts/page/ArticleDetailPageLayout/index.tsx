import React, { ReactNode } from 'react';
import { ContentLayout, FullContentLayout } from 'layouts/common/ContentLayout';
import { ArticleDetailPageLayoutContainer } from './style';

interface IArticleDetailPageLayoutProps {
	ArticleHeader: ReactNode;
	ArticleKeywordList: ReactNode;
	ArticleContent: ReactNode;
	RecommendedArticleList: ReactNode;
	Footer: ReactNode;
}

function ArticleDetailPageLayout(props: IArticleDetailPageLayoutProps) {
	const { ArticleHeader, ArticleKeywordList, ArticleContent, RecommendedArticleList, Footer } = props;

	return (
		<ArticleDetailPageLayoutContainer>
			<ContentLayout>
				<div className="article-header">{ArticleHeader}</div>
				<div className="article-keyword-list">{ArticleKeywordList}</div>
				<div className="article-content">{ArticleContent}</div>
				<div className="recommended-article-list">{RecommendedArticleList}</div>
			</ContentLayout>
			<FullContentLayout>
				<div className="footer">{Footer}</div>
			</FullContentLayout>
		</ArticleDetailPageLayoutContainer>
	);
}

export default ArticleDetailPageLayout;
