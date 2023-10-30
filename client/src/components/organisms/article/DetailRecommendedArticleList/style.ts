import styled from 'styled-components';

export const DetailRecommendedArticleListContainer = styled.div`
	margin-top: 1rem;
	display: flex;
	flex-direction: column;
	gap: 1rem;
	margin-bottom: 10rem;

	.recommended-article-header {
		font-size: 1.5rem;
		font-weight: bold;
		color: var(--main-color);
		margin: 1rem 0;
	}

	.swiper {
		width: 100%;

		.swiper-slide {
			div {
				width: 150px;
				height: 150px;

				img {
					width: 150px;
					height: 150px;
				}

				h2 {
					color: white;
					bottom: 0;
					position: absolute;
					z-index: 11;
					font-size: 1rem;
					margin: 1rem;
					white-space: normal;
					display: -webkit-box;
					-webkit-line-clamp: 2;
					-webkit-box-orient: vertical;
					overflow: hidden;
					transition: all 0.1s;
				}

				&:hover {
					h2 {
						transform: translateY(-20%);
					}
				}
			}
		}
	}
`;
