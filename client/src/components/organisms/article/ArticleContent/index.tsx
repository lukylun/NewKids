/* eslint-disable react/no-danger */
import React from 'react';
import { FullContentLayout } from 'layouts/common/ContentLayout';
import { ArticleContentContainer } from './style';
import Dictionary from '../Dictionary';

interface IArticleContentProps {
	content: string;
}
function ArticleContent(props: IArticleContentProps) {
	const { content } = props;
	return (
		<ArticleContentContainer>
			<FullContentLayout>
				<div className="article-content" dangerouslySetInnerHTML={{ __html: content }} />
			</FullContentLayout>
			<Dictionary />
		</ArticleContentContainer>
	);
}

export default ArticleContent;
