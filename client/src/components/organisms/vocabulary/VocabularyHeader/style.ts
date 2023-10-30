import styled from 'styled-components';

export const VocabularyHeaderContainer = styled.div`
	margin-top: 3rem;
	display: flex;
	justify-content: space-between;

	.voca-menu {
		display: flex;
		gap: 30px;

		.voca-total-button,
		.voca-know-button {
			width: 250px;
			height: 50px;
			display: flex;
			gap: 30px;
			align-items: center;
			justify-content: space-evenly;
			border: solid 1px var(--gray-200);
			border-radius: var(--radius-l);
			> p {
				font-size: 18px;
				font-weight: bold;
			}
			.total-count {
				color: var(--gray-500);
			}

			.know-count {
				color: var(--sub-color-1);
			}
		}
	}
`;
