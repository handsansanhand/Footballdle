
FROM python:3.11-slim

WORKDIR /app

COPY . /app

RUN pip install --no-cache-dir -r requirements.txt

#command to run
CMD ["python", "pipeline_run.py"]
