import styled from 'styled-components';

export const SearchOptionsContainer = styled.div`
	border: 2px solid var(--gray-300);
	border-radius: var(--radius-m);
	padding: 1rem;

	.wrapper {
		display: flex;
		flex-direction: row;
		justify-content: space-between;

		.left {
			display: flex;
			flex-direction: column;
			gap: 1rem;
			.date-input {
				display: flex;
				flex-direction: row;

				input {
					text-align: center;
					font-size: 1.1rem;
					width: 300px;
				}

				span {
					display: flex;
					flex-direction: column;
					justify-content: center;
					margin: 0 1rem;
					font-size: 1.5rem;
				}
			}

			.filter {
				display: flex;
				flex-direction: row;
				gap: 0.5rem;
			}
		}

		.right {
			button {
				width: 100px;
				height: 100px;
			}
		}
	}
`;
