

# Top-K Analytics Service

A **production-grade Spring Boot backend** that ingests user activity and computes **Top-K most viewed items**, with support for **time-windowed (trending) analytics**, **database-level optimization**, and **efficient in-memory ranking**.

This project focuses on **system design, performance, and correctness** - not CRUD boilerplate.

<img width="1485" height="754" alt="analytics " src="https://github.com/user-attachments/assets/544edf0e-72c0-4408-ada8-f6b463801759" />


## What This Project Solves

Real platforms (travel, e-commerce, content) constantly ask:

* What are the **Top 10 most viewed items**?
* What is **trending in the last 15 minutes**?
* How do we answer this **efficiently as data grows**?

Naive approaches (sorting everything or relying only on SQL `ORDER BY`) break at scale.
This service demonstrates how to solve **Top-K analytics correctly** using the right mix of **database aggregation** and **in-memory algorithms**.

---

## Key Engineering Ideas

* **Event-driven design** (immutable view events)
* **Database-side aggregation** to reduce data volume
* **Min-Heap (PriorityQueue)** for efficient Top-K ranking
* **Time-window analytics** using timestamp filtering
* **Clear layered architecture** for maintainability
* **Designed to evolve** (Redis / Kafka ready)

---

<img width="600" height="600" alt="gausul _ archetecting" src="https://github.com/user-attachments/assets/5ffa2e21-1165-4331-8729-28affacd130c" />


## Architecture Overview

```
Client
  ↓
REST Controller (Validation + Routing)
  ↓
Service Layer (Business Logic)
  ↓
Repository Layer (Aggregation Queries)
  ↓
MySQL (Indexed Event Store)
```

Each layer has a **single responsibility** and can evolve independently.

---

## How the System Works

### 1. Event Ingestion

* Client sends a view event
* Input is validated
* Event is stored as an **immutable record**

### 2. Aggregation

* Database groups events by item
* Optional time window is applied (`last X minutes`)

### 3. Top-K Ranking

* Aggregated results are processed using a **Min-Heap**
* Heap size is limited to **K**
* Ensures **O(N log K)** performance

### 4. Response

* Clean JSON response
* No internal details leaked

---

## Data Model

### `view_event` (append-only)

| Column      | Description            |
| ----------- | ---------------------- |
| `id`        | Primary key            |
| `item_id`   | Viewed item identifier |
| `viewed_at` | Server-side timestamp  |

**Why append-only?**
Analytics systems store **facts**, not counters. Aggregates are derived later.

---

## Tech Stack

* **Java 17**
* **Spring Boot**
* **Spring Data JPA**
* **MySQL 8**
* **Bean Validation**
* **PriorityQueue (Min-Heap)**

Minimal dependencies. Maximum clarity.

---

## API Endpoints

### ➕ Record a View

```
POST /analytics/view
```

```json
{
  "itemId": "property_123"
}
```

---

### Aggregated Counts

```
GET /analytics/counts
```

Returns raw aggregated counts (useful for debugging and analysis).

---

### Top-K (All-Time)

```
GET /analytics/top?k=5
```

---

### Top-K (Time Window)

```
GET /analytics/top?k=5&minutes=30
```

Returns Top-K items viewed in the **last X minutes**.

---

## Top-K Algorithm (Why This Works)

Instead of sorting all items (`O(N log N)`), this project uses:

### Min-Heap Strategy

* Maintain a heap of size **K**
* Remove the smallest element when size exceeds K
* Heap always contains Top-K elements

### Complexity

| Aspect        | Cost       |
| ------------- | ---------- |
| Aggregation   | O(N)       |
| Top-K Ranking | O(N log K) |
| Memory        | O(K)       |

This is the **optimal approach** for Top-K problems.

---

## Database Optimization

### Composite Index

```sql
(viewed_at, item_id)
```

### Why it matters

* Speeds up time-window filtering
* Improves `GROUP BY` performance
* Avoids full table scans

Balanced for **read-heavy analytics workloads**.

---

## Validation & Error Handling 🛡️

* Request body validation (`itemId` not blank)
* Query param validation (`k`, `minutes >= 1`)
* Global exception handling
* Clean `400` responses, no stack traces

Designed for **API reliability**, not just happy paths.

---

## Performance Characteristics

* Efficient under large datasets
* Memory usage bounded by **K**
* Database does heavy lifting
* Application layer focuses on ranking

---

## Designed for Evolution

This system is intentionally built to evolve into:

* **Redis** → real-time Top-K (Sorted Sets)
* **Kafka** → streaming ingestion
* **Rolling windows** and pre-aggregation
* **Horizontal scaling**

## What This Project Demonstrates

* Strong grasp of **data structures & algorithms**
* Correct **DB vs application** responsibility split
* Performance-driven design
* Clean Spring Boot architecture
* Real-world analytics thinking

This is **not a tutorial project**.
It’s a **backend systems showcase**.

## 👤 Author
Backend developer focused on **scalable systems, performance optimization, and clean architecture**.
## Gausul Wara 
## https://www.linkedin.com/in/gausul-wara-643783256/

### Documentation complete. Thank you for your time.


