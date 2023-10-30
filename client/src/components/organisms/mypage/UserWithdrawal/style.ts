import styled from 'styled-components';

export const UserWithdrawalWrapper = styled.div`
	.user-withdrawal-form {
		width: 100%;
		height: 100%;
		margin-top: 3.5rem;
		display: flex;
		flex-direction: column;

		.user-withdrawal-container {
			width: 100%;
			display: flex;
			justify-content: center;
			flex-direction: column;
			align-items: center;
			.user-withdrawal-input {
				width: 80%;
				> * {
					margin-bottom: 1.5rem;
				}
			}
		}
		.user-withdrawal-title {
			margin-bottom: 3rem;
			display: flex;
			justify-content: center;
			align-items: center;
			gap: 10px;

			svg {
				height: 70px;
				width: 60px;
				fill: var(--danger-color);
			}

			h1 {
				font-size: 30px;
				font-weight: bold;
				color: var(--gray-500);
			}
		}
		.user-withdrawal-password {
			display: flex;
			justify-content: center;
			gap: 5px;
			width: 100%;
			margin-bottom: 1rem;

			h2 {
				display: flex;
				width: 80%;
				font-size: 20px;
				font-weight: bold;
				color: var(--gray-500);
			}
		}
		.user-withdrawal-button {
			width: 100%;
			display: flex;
			justify-content: center;
			gap: 20px;
			margin-top: 1rem;
			.button-container {
				width: 80%;
				display: flex;
				gap: 15px;
			}
		}
	}
`;
