import styled from 'styled-components';

export const UserLevelWrapper = styled.div`
	width: 100%;
	margin-top: 1rem;

	.user-level-wrapper {
		display: flex;
		gap: 20px;
		justify-content: center;
		align-items: end;
	}

	.user-level-image {
		width: 40px;
		height: 40px;
	}

	.level-title {
		font-size: 30px;
		color: var(--sub-color-1);
		font-weight: bold;
	}
	.level-number {
		font-size: 30px;
		color: var(--main-color);
		font-weight: bold;
	}
`;
