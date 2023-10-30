import styled from 'styled-components';

export const MyCollectionArticleListWrapper = styled.div`
	.collection-list-item {
		margin-top: 1rem;
		background-color: var(--main-color);
		width: 100%;
		height: 500px;
	}
	width: 100%;
	height: 100%;
	margin-top: 2rem;

	display: flex;
	flex-wrap: wrap;
	justify-content: space-between;
	align-content: flex-start;

	.article-list-text {
		width: calc(25% - 10px);
		margin-bottom: 10px;
		img {
			height: 150px;
			width: 250px;
			border-radius: 20%;
		}
		p {
			font-size: 24px;
			font-weight: bold;
			margin-bottom: 2rem;
		}
	}
`;
