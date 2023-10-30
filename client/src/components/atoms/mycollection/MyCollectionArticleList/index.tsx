import React from 'react';
import { IMyArticleDetail } from 'types/article';
import useMovePage from 'hooks/useMovePage';
import articleImage from 'assets/imgs/profile-level.png';
import { MyCollectionArticleListWrapper } from './style';

interface IMyCollectionArticleProps {
	articles: IMyArticleDetail[];
}

function MyCollectionArticle(props: IMyCollectionArticleProps) {
	const { articles } = props;

	const [movePage] = useMovePage();

	const renderArticleItems = () => {
		return articles.map((item) => {
			return (
				<div className="article-list-text" key={item.articleId}>
					{item.thumbnailImg && (
						<img
							src={item.thumbnailImg}
							alt=""
							onClick={() => movePage(`/article/${item.articleId}`)}
							role="presentation"
						/>
					)}

					{!item.thumbnailImg && <img src={articleImage} alt="" />}
					<p onClick={() => movePage(`/article/${item.articleId}`)} role="presentation">
						{item.title}
					</p>
				</div>
			);
		});
	};

	return <MyCollectionArticleListWrapper>{renderArticleItems()}</MyCollectionArticleListWrapper>;
}

export default MyCollectionArticle;
