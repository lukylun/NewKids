import styled from 'styled-components';

export const MyActivityArticleListWrapper = styled.div`
	width: 100%;
	height: 100%;

	display: flex;
	flex-wrap: wrap;
	gap: 10px;
	align-content: flex-start;

	.article-list-text {
		width: 300px;
		margin-bottom: 10px;
		img {
			height: 150px;
			width: 250px;
			border-radius: var(--radius-m);
			margin-bottom: 1rem;
		}
		img:hover,
		p:hover {
			cursor: pointer;
		}
		p {
			font-size: 24px;
			font-weight: bold;
			margin-bottom: 2rem;
		}
	}
`;
