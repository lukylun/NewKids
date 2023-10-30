import { styled } from 'styled-components';

export const ArticleKeywordListContainer = styled.div`
	margin-bottom: 3rem;
	display: flex;
	flex-direction: column;
	gap: 1rem;

	.header {
		font-size: 1.1rem;
		color: var(--main-color);
	}

	.keywords {
		display: flex;
		flex-direction: row;
		gap: 0.5rem;
	}
`;
