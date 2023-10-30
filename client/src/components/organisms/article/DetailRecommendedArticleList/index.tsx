import React from 'react';
import { IArticle } from 'types/article';
import SquareArticleListItem from 'components/atoms/article/SquareArticleListItem';
import useMovePage from 'hooks/useMovePage';
import { Swiper, SwiperSlide } from 'swiper/react';
import 'swiper/css';
import { DetailRecommendedArticleListContainer } from './style';

interface IDetailRecommendedArticleListProps {
	articles: IArticle[];
}
function DetailRecommendedArticleList(props: IDetailRecommendedArticleListProps) {
	const { articles } = props;
	const [movePage] = useMovePage();

	return (
		<DetailRecommendedArticleListContainer>
			<h3 className="recommended-article-header">현재 보고있는 기사와 비슷한 기사에요!</h3>
			{articles.length ? (
				<Swiper
					className="swiper"
					breakpoints={{
						0: {
							slidesPerView: 4,
							spaceBetween: 5,
						},
						756: {
							slidesPerView: 5,
							spaceBetween: 10,
						},
						1000: {
							slidesPerView: 6,
							spaceBetween: -20,
						},
						1500: {
							slidesPerView: 7,
							spaceBetween: -20,
						},
					}}
					navigation
				>
					{articles.map((el) => (
						<SwiperSlide className="swiper-slide" key={el.articleId}>
							<SquareArticleListItem
								article={el}
								key={el.articleId}
								handleClick={() => movePage(`/article/${el.articleId}`)}
							/>
						</SwiperSlide>
					))}
				</Swiper>
			) : (
				<div>추천 기사 불러오는 중...</div>
			)}
		</DetailRecommendedArticleListContainer>
	);
}

export default DetailRecommendedArticleList;
