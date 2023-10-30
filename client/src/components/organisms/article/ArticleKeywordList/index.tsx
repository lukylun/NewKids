import React from 'react';
import { IKeyword } from 'types/keyword';
import KeywordListItem from 'components/atoms/common/KeywordListItem';
import useMovePage from 'hooks/useMovePage';
import { ArticleKeywordListContainer } from './style';

interface IArticleKeywordListProps {
	keywords: IKeyword[];
}

function ArticleKeywordList(props: IArticleKeywordListProps) {
	const { keywords } = props;
	const [movePage] = useMovePage();

	return (
		<ArticleKeywordListContainer>
			<h3 className="header">주요 키워드</h3>
			<div className="keywords">
				{keywords.length ? (
					keywords.map((el) => (
						<KeywordListItem
							text={el.word}
							key={el.keywordId}
							handleClick={() => movePage(`/article?search=${el.word}`)}
						/>
					))
				) : (
					<div />
				)}
			</div>
		</ArticleKeywordListContainer>
	);
}

export default ArticleKeywordList;
