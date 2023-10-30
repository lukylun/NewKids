import styled from 'styled-components';

export const PageLayoutContainer = styled.div<{ $reducePadding: boolean | undefined }>`
	position: relative;
	min-height: 100vh;
	padding-top: ${({ $reducePadding }) => ($reducePadding ? '76px' : '166px')};
`;
