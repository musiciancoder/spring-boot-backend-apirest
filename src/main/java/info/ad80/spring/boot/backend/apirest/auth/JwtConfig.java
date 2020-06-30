package info.ad80.spring.boot.backend.apirest.auth;

//Video 129
//Esta es la clase para firmar el token
public class JwtConfig {
	
	public static final String LLAVE_SECRETA="alguna.clave.secreta.123456678";
	
	
	public static final String RSA_PRIVADA="-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEpAIBAAKCAQEAzM+Wk93LT/jvPSid+ieHR+TVLgXDZfCUQMkWLZh3Q93KfH2a\r\n" + 
			"Yr4gU2SfdfQ8L+oBvpIh0EAcivGmmjqPs9GLt3dojTSClSkE46naiU9KckVfM/RL\r\n" + 
			"/jQSMNs72FtW5XIZvSjpQC57Nrh3QZz/0BHtmyuklbK2vyFyWA3Dwqcu+9lFs3vr\r\n" + 
			"I1p6xVnSZtxpJNWgffRwt3cghxXk4z1x8W5yZ83zSzwi8tyfD+Il8J330dBfQQ+5\r\n" + 
			"BEolYUO3GD9VdobGm/Gdrfh3e9Gkpi0k7GH8rozw23pDsdTaDaKdj5nKxbLb0mtE\r\n" + 
			"lz15ZDxvQXzKvsnNoRmw/9m/dxfgSrIsp5LG4wIDAQABAoIBAG+urySYqFpGm2aV\r\n" + 
			"NHgJgApjLSA4glGfO6iMPqzEXBzE37q83ygDFyuCTjYK5ZsXFrZth0TBAQ9/MGMf\r\n" + 
			"lebvWCnGUlvVi3N8966Rm5qOg0EGtSBmkd3fVSARHHQhcqQRcRmmKnMWJZg0RMuh\r\n" + 
			"qhky/YdE6C29U0usGGcNsnb9pXAtErGEUF5/aF3a49v+BBpwquIOoig6o8G4ri4Q\r\n" + 
			"2dx17jhhKxCjkN6XJ/CCvkHe+3MvisAuF7ri6bQu0dHKvdP6pf60nHWqTCxV+IWg\r\n" + 
			"yKEeX/zGB1QDwmwIAcUOvbdllCmo514ReddWea6wy++oBFKpiQJ2q4y0xzXfzRNS\r\n" + 
			"lsD3CCkCgYEA+4rsvSQiVPexQNB9ByRq+mJCy1TF/GPAVQ110NyFomVbJHFfl323\r\n" + 
			"gVnDIGZSlCYHm2bB8mipTfTeUVGKreWsShplj6f7IhLYHMibNx3kH2K7+eR4bo7P\r\n" + 
			"PtVC54Pm0OWrtyI0CTyKWAen+YMsIv57X7Q/IbhTinlF+34w8Ej7uc8CgYEA0HCs\r\n" + 
			"c0h7QXskE2Ahwx7OOb+tAKnBOSN+8EkW7AJSYBXv+pXRjz715A4kBCc8ll2S5Xrd\r\n" + 
			"ylNuNL+CgKuo35fXcSvCsG5XWcLJ+UBaC4kdGZF84SULxbVPiNTItRPgrpe/jw77\r\n" + 
			"aRvJwJi+BIseRmF8IJpedcgxEe+i3bhxQsMx6q0CgYEA5+z9vp70cLc4XXRd8xnm\r\n" + 
			"8RlsUKHHMqCek00n1f2d0U/2qzFFXB5z9fXXbaYqeaJe3S/vnxxVDiW6Xev7G79q\r\n" + 
			"DaEtBzlw2cfaLx/fzP/od+/HNe8sA3P1Lg/f8iqxxOYeX2pP5DxHqkbU37/MEC/n\r\n" + 
			"fSRqGSMlE8YhenKtmL8SKg0CgYEAygTKPxl6zGYq+tRrJI+Q1Iufg0sDyPtMJlrQ\r\n" + 
			"aoF9kT7xze6yoqEk9Jl38saMhDfBDcTdfSELaPhL5aV6EOs7laYSFal747VUx8zE\r\n" + 
			"+PU1TPijDhs3o7EsmrOQq1/OnajZsi+VH7QdABuJ3yFugEwvX/H9py4V0Qpu0XHX\r\n" + 
			"5BsHICkCgYA1GlFIe9odiye5zSlwju8mYjAQeY6b6ax7FHUSpgqd6s0IERzXHtH4\r\n" + 
			"AJnviBe6Zh0cUnKgL2HFPeWd8twf7oZ8Ta6NYrgKnpSU76PbttlvA8/EhJS4EK6A\r\n" + 
			"3hB/oBWOIqewDLDPTdgjMOVSEkDwn9GftDkWwQP9cSWcCgYUkCksuA==\r\n" + 
			"-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLICA="-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzM+Wk93LT/jvPSid+ieH\r\n" + 
			"R+TVLgXDZfCUQMkWLZh3Q93KfH2aYr4gU2SfdfQ8L+oBvpIh0EAcivGmmjqPs9GL\r\n" + 
			"t3dojTSClSkE46naiU9KckVfM/RL/jQSMNs72FtW5XIZvSjpQC57Nrh3QZz/0BHt\r\n" + 
			"myuklbK2vyFyWA3Dwqcu+9lFs3vrI1p6xVnSZtxpJNWgffRwt3cghxXk4z1x8W5y\r\n" + 
			"Z83zSzwi8tyfD+Il8J330dBfQQ+5BEolYUO3GD9VdobGm/Gdrfh3e9Gkpi0k7GH8\r\n" + 
			"rozw23pDsdTaDaKdj5nKxbLb0mtElz15ZDxvQXzKvsnNoRmw/9m/dxfgSrIsp5LG\r\n" + 
			"4wIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";


}
