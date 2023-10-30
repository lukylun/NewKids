import styled from 'styled-components';

export const MyActivityArticleContainer = styled.div`
	margin-top: 1rem;
	.article-title-text {
		display: flex;

		.gray-color-text {
			color: var(--gray-500);
			font-size: 24px;
			font-weight: bold;
		}
		.sub-color-text {
			color: var(--sub-color-1);
			font-size: 24px;
			font-weight: bold;
		}
	}

	.article-list-box {
		margin-top: 2rem;
		width: 100%;
		height: 100%;
	}
`;
