import React from 'react';
import MyActivityArticleList from 'components/atoms/myactivity/MyActivityArticleList';
import { IMyArticleDetail } from 'types/article';
import { MyActivityArticleContainer } from './styles';

interface IMyActivityArticleProps {
	articles: IMyArticleDetail[];
}
function MyActivityArticle(props: IMyActivityArticleProps) {
	const { articles } = props;

	return (
		<MyActivityArticleContainer>
			<div className="article-title-text">
				<p className="gray-color-text">내가&nbsp;</p>
				<p className="sub-color-text">최근 본 기사</p>
				<p className="gray-color-text">에요</p>
			</div>
			<div className="article-list-box">
				<MyActivityArticleList articles={articles} />
			</div>
		</MyActivityArticleContainer>
	);
}

export default MyActivityArticle;
