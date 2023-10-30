import styled from 'styled-components';

export const PaginationContainer = styled.div`
	display: flex;
	flex-direction: row;
	justify-content: center;
	align-items: center;
	gap: 0.5rem;
`;

export const PaginationItemWrapper = styled.button<{ $isActive: boolean }>`
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	width: 40px;
	height: 40px;
	border-radius: var(--radius-l);
	padding: 1rem;
	background-color: ${({ $isActive }) => ($isActive ? 'var(--main-color)' : 'none')};
	color: ${({ $isActive }) => ($isActive ? 'var(--white-color)' : 'var(--black-500)')};
`;
