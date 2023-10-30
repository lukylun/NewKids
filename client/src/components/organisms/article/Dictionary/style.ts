import styled from 'styled-components';

export const DictionaryContainer = styled.div`
	min-width: 300px;
	width: 30%;
	height: 450px;
	border: 2px solid var(--sub-color-2);
	border-radius: var(--radius-m);
	top: 10rem;
	position: sticky;
	padding: 1rem;
	display: flex;
	flex-direction: column;
	gap: 1rem;
`;

export const WordListContainer = styled.div`
	overflow-y: scroll;
	display: flex;
	flex-direction: column;
	gap: 2rem;
`;

export const WordListItemContainer = styled.div`
	display: flex;
	flex-direction: column;
	gap: 0.5rem;

	.word-header {
		font-weight: bold;
	}

	.add {
		font-weight: bold;
		color: var(--gray-500);
		background-color: var(--sub-color-2);
		border-radius: var(--radius-l);
		padding: 0.5rem 0;
	}
`;
