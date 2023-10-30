import styled from 'styled-components';

export const KeywordListItemContainer = styled.div`
	width: fit-content;
	border: 2px solid var(--gray-400);
	padding: 0.5rem 1rem;
	border-radius: var(--radius-l);
	transition: all 0.1s;

	&:hover {
		background-color: var(--gray-200);
		cursor: pointer;
		transform: scale(1.05);
	}
`;
