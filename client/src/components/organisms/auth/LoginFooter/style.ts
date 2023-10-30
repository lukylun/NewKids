import styled from 'styled-components';

export const LoginFooterContainer = styled.div`
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	gap: 0.5rem;

	.team {
		margin-bottom: 1rem;
	}
	.copyright {
		font-weight: bold;
		display: flex;
		align-items: center;
		gap: 2rem;

		img {
			width: 8rem;
			height: 2rem;
		}
	}
`;
