import styled from 'styled-components';

export const UserDetailContainer = styled.div`
	width: 100%;
	height: 110%;

	.mypage-form {
		margin-top: 1.5rem;
	}

	hr {
		margin-top: 1rem;
		height: 3px;
		border: 0;
		background: var(--gray-200);
	}

	.mypage-title {
		display: flex;
		justify-content: space-between;
		align-items: center;
		.mypage-button {
			display: flex;
			gap: 10px;
			> Button {
				font-size: 12px;
			}
		}
	}

	p {
		color: var(--main-color);
		margin-top: 30px;
		font-size: 20px;
	}
`;
