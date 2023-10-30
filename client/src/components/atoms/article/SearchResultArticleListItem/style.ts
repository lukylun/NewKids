import styled from 'styled-components';

export const SearchResultArticleListItemContainer = styled.div`
	border-radius: var(--radius-m);
	display: flex;
	flex-direction: row;
	gap: 1rem;
	color: var(--gray-500);
	transition: all 0.1s;

	&:hover {
		background-color: var(--gray-200);
		cursor: pointer;
		transform: scale(1.05);
	}

	img {
		border-radius: var(--radius-m);
		width: 200px;
		min-width: 200px;
		height: 150px;
	}

	.article {
		width: 100%;
		display: flex;
		flex-direction: column;
		justify-content: space-around;

		.content {
			h3 {
				font-size: 1.3rem;
				font-weight: bold;
				white-space: normal;
				display: -webkit-box;
				-webkit-line-clamp: 2;
				-webkit-box-orient: vertical;
				overflow: hidden;
			}

			display: flex;
			flex-direction: column;
			gap: 0.5rem;
			color: var(--gray-500);
		}

		.article-info {
			color: var(--gray-300);
		}
	}
`;
