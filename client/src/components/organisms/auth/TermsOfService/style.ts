import styled from 'styled-components';

export const TermsOfServiceContainer = styled.div`
	position: relative;
	height: calc(100vh - 76px);
	padding-top: 3rem;

	.agree-form {
		padding: 3rem;
		border: 1px solid var(--gray-300);
		border-radius: var(--radius-s);
		display: flex;
		flex-direction: column;
		gap: 3rem;
	}
	.agree {
		display: flex;
		flex-direction: column;
		gap: 1rem;
	}

	.agree-all {
		p {
			font-size: 1.2rem;
		}
	}

	textarea {
		font-family: 'Pretendard';
		width: 100%;
		height: 10rem;
		resize: none;
	}

	.next-btn {
		button {
			position: absolute;
			bottom: 2rem;
		}
	}
`;
