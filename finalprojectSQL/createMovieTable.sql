USE [movie]
GO
/****** Object:  Table [dbo].[movie]    Script Date: 2019/10/16 上午 10:27:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[movie](
	[id] [int] NULL,
	[name] [nvarchar](max) NULL,
	[link] [nvarchar](max) NULL,
	[movie_content] [nvarchar](max) NULL,
	[FileLocation] [nvarchar](max) NULL,
	[Type] [nvarchar](max) NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
